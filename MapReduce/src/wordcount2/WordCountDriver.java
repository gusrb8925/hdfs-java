package wordcount2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		if(args.length != 2) {
			System.out.printf("Usage: WordCount <input dir> <output div>\n");
			System.exit(-1);
		}
		
		Job job = new Job();
		
		job.setJarByClass(WordCountDriver.class);
		
		job.setJobName("Word Count");
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordMapper.class);
		job.setReducerClass(SumReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);

	}

}
